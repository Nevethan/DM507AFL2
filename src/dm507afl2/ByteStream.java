package dm507afl2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 *
 * @author Eger
 */
public class ByteStream implements Iterable<Integer>, Closeable{
    // Underlying byte stream to read from.
    private InputStream input;

    // Buffer of up to 8 bits from the most recently read byte of the
    // underlying byte input stream. Is an int in the range 0 to 255
    // if bits are available, or is -1 if the end of stream is
    // reached.
    private int nextBits;

    // Always between 0 and 8, inclusive.
    private int numBitsRemaining;

    private boolean isEndOfStream;


    // Creates a bit input stream based on the given byte input stream.
    public ByteStream(InputStream in) {
        if (in == null)
            throw new NullPointerException("No input stream given");
        input = in;
        numBitsRemaining = 0;
        isEndOfStream = false;
        try {
            nextBits = input.read();
        } catch (IOException e) {
            nextBits = -1;
        }
    }


    // Reads a bit from the stream. Returns 0 or 1 if a bit is
    // available, or -1 if the end of stream is reached. The end of
    // stream always occurs on a byte boundary.
    public int readBit() throws IOException {
        if (isEndOfStream)
            return -1;
        if (numBitsRemaining == 0) {
            nextBits = input.read();
            if (nextBits == -1) {
                isEndOfStream = true;
                return -1;
            }
            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (nextBits >>> numBitsRemaining) & 1;
    }


    public int readByte() throws IOException {
        int output = 0;
        int nextBit;
        int bitsAdded = 0;
        while(bitsAdded < 8){
            nextBit = readBit();
            if (nextBit == -1)
                return -1;
            output = output << 1 | nextBit;
            bitsAdded++;
        }
        return output;
    }


    // Closes this stream and the underlying InputStream.
    public void close() throws IOException {
        input.close();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>()  {
            @Override
            public boolean hasNext() {
                return nextBits!=-1;
            }

            @Override
            public Integer next(){
                int nextBits1 = ByteStream.this.nextBits;
                try {
                    nextBits = input.read();
                } catch (IOException e) {
                    nextBits = -1;
                }
                return nextBits1;
            }
        };
    }
}