/*
 * codesearch "binaryfile openmap"
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.io;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * The BinaryFile is the standard object used to access data files. It
 * acts like a RandomAccessFile, but will work on jar file contents
 * and URLs, too. The source of the data is isolated through the
 * InputReader interface.
 */
public class BinaryFile {
    private InputReader inputReader = null;

    /**
     * The byte order of the underlying file. (<code>true</code>==
     * MSB-First == big-endian)
     */
    protected boolean MSBFirst = false;

    /**
     * Constructs a new BinaryFile with the specified file as the
     * input. The default byte-order is LSB first. Reads start at the
     * first byte of the file.
     *
     * @param f the file to be opened for reading
     * @exception IOException pass-through errors from opening a
     *            RandomAccessFile with f
     * @see java.io.RandomAccessFile
     */
    public BinaryFile(File f) throws IOException {
        inputReader = new FileInputReader(f);
    }

    /**
     * Constructs a new BinaryFile with the specified file as the
     * input. The byte-order is undefined. Reads start at the first
     * byte of the file. This constructor looks for the file with the
     * string given, and will call the correct constructor as
     * appropriate. If the string represents a file available locally,
     * then the BinaryFile will be accessed with a FileInputReader
     * using a RandomAccessFile. If it's only available as a resource,
     * then a StreamInputReader will be used. The name should be a
     * path to a file, or the name of a resource that can be found in
     * the classpath, or a URL.
     *
     * @param name the name of the file to be opened for reading
     * @exception IOException pass-through errors from opening the
     *            file.
     */
    public BinaryFile(String name) throws IOException {
        try {
            File file = null;
            URL url = null;

                file = new File(name);
           
            if (file != null && file.exists()) {
                // If the string represents a file, then we want to
                // use the RandomAccessFile aspect of the BinaryFile.
                setInputReader(new FileInputReader(file));
            } 

            if (inputReader == null) {
                throw new FileNotFoundException("BinaryFile can't find: "
                        + name);
            }

        } catch (IOException ioe) {
            throw ioe;
        }
    }



    /**
     * A simple test method to determine if a file or directory,
     * represented by a string, can be found by the current Java
     * environment. Uses the same tests as BinaryFile constructor for
     * tracking down a file.
     *
     * @param name A path to a file, a URL, or a path to a jar file
     *        entry.
     */
    public static boolean exists(String name) {
        boolean exists = false;
            File file = null;
            URL url = null;

                file = new File(name);
          
            if (file != null && file.exists()) {
                exists = true;
            } 

       
        return exists;
    }

    /**
     * Get the source name from the input reader.
     */
    public String getName() {
        if (inputReader != null) {
            return inputReader.getName();
        }
        return null;
    }

    /**
     * Get the inputReader used for accessing the file, for quering
     * purposes. Don't use it to get data, or the file pointers may
     * get messed up.
     */
    public InputReader getInputReader() {
        return inputReader;
    }

    /**
     * Set the input reader used by the BinaryFile. Make sure it's
     * intialized properly.
     */
    public void setInputReader(InputReader reader) {
        inputReader = reader;
    }

    /**
     * Set the byte-ordering used to read shorts, int, etc.
     *
     * @param msbfirst <code>true</code>= MSB first,
     *        <code>false</code>= LSB first
     */
    public void byteOrder(boolean msbfirst) {
        MSBFirst = msbfirst;
    }

    /**
     * Accessor for the byte ordering used to read multibyte types.
     *
     * @return byte ordering
     */
    public boolean byteOrder() {
        return MSBFirst;
    }

    /**
     * Skip over n bytes in the input file
     *
     * @param n the number of bytes to skip
     * @return the actual number of bytes skipped. annoying, isn't it?
     * @exception IOException Any IO errors that occur in skipping
     *            bytes in the underlying file
     */
    public long skipBytes(long n) throws IOException {
        return inputReader.skipBytes(n);
    }

    /**
     * Get the index of the next character to be read
     *
     * @return the index
     * @exception IOException Any IO errors that occur in accessing
     *            the underlying file
     */
    public long getFilePointer() throws IOException {
        return inputReader.getFilePointer();
    }

    /**
     * Set the index of the next character to be read.
     *
     * @param pos the position to seek to.
     * @exception IOException Any IO Errors that occur in seeking the
     *            underlying file.
     */
    public void seek(long pos) throws IOException {
        inputReader.seek(pos);
    }

    /**
     * The length of the InputReader source.
     */
    public long length() throws IOException {
        return inputReader.length();
    }

    /**
     * Return how many bytes left to be read in the file.
     *
     * @return the number of bytes remaining to be read (counted in
     *         bytes)
     * @exception IOException Any IO errors encountered in accessing
     *            the file
     */
    public long available() throws IOException {
        return inputReader.available();
    }

    /**
     * Closes the underlying file
     *
     * @exception IOException Any IO errors encountered in accessing
     *            the file
     */
    public void close() throws IOException {
        if (inputReader != null) {
            inputReader.close();
        }
        inputReader = null;
    }

    /**
     * Read from the file.
     *
     * @return one byte from the file. -1 for EOF
     * @exception IOException Any IO errors encountered in reading
     *            from the file
     */
    public int read() throws IOException {
        return inputReader.read();
    }

    /**
     * Read from the file
     *
     * @param b The byte array to read into
     * @param off the first array position to read into
     * @param len the number of bytes to read
     * @return the number of bytes read
     * @exception IOException Any IO errors encountered in reading
     *            from the file
     */
    public int read(byte b[], int off, int len) throws IOException {
        return inputReader.read(b, off, len);
    }

    /**
     * Read from the file.
     *
     * @param b the byte array to read into. Equivelent to
     *        <code>read(b, 0, b.length)</code>
     * @return the number of bytes read
     * @exception IOException Any IO errors encountered in reading
     *            from the file
     * @see java.io.RandomAccessFile#read(byte[])
     */
    public int read(byte b[]) throws IOException {
        return inputReader.read(b);
    }

    /**
     * Read from the file.
     *
     * @param howmany the number of bytes to read
     * @param allowless if we can return fewer bytes than requested
     * @return the array of bytes read.
     * @exception FormatException Any IO Exceptions, plus an
     *            end-of-file encountered after reading some, but now
     *            enough, bytes when allowless was <code>false</code>
     * @exception EOFException Encountered an end-of-file while
     *            allowless was <code>false</code>, but NO bytes
     *            had been read.
     */
    public byte[] readBytes(int howmany, boolean allowless)
            throws EOFException, FormatException {

        return inputReader.readBytes(howmany, allowless);
    }

    /**
     * Reads and returns a single byte, cast to a char
     *
     * @return the byte read from the file, cast to a char
     * @exception EOFException the end-of-file has been reached, so no
     *            chars where available
     * @exception FormatException a rethrown IOException
     */
    public char readChar() throws EOFException, FormatException {
        try {
            int retv = inputReader.read();

            if (retv == -1) {
                throw new EOFException("Error in ReadChar, EOF reached");
            }
            return (char) retv;
        } catch (IOException i) {
            throw new FormatException("readChar IOException: " + i.getMessage());
        }
    }

    /**
     * Reads and returns a short.
     *
     * @return the 2 bytes merged into a short, according to the
     *         current byte ordering
     * @exception EOFException there were less than 2 bytes left in
     *            the file
     * @exception FormatException rethrow of IOExceptions encountered
     *            while reading the bytes for the short
     * @see #read(byte[])
     */
    public short readShort() throws EOFException, FormatException {
        //MSBFirst must be set when we are called
        return MoreMath.BuildShort(readBytes(2, false), MSBFirst);
    }

    /**
     * Reads and returns a long
     *
     * @return the 4 bytes merged into a long, according to the
     *         current byte ordering
     * @exception EOFException there were less than 4 bytes left in
     *            the file
     * @exception FormatException rethrow of IOExceptions encountered
     *            while reading the bytes for the integer
     * @see #read(byte[])
     */
    public int readInteger() throws EOFException, FormatException {
        //MSBFirst must be set when we are called
        return MoreMath.BuildInteger(readBytes(4, false), MSBFirst);
    }

    public void readIntegerArray(int vec[], int offset, int len)
            throws EOFException, FormatException {
        for (int i = 0; i < len; i++) {
            vec[offset++] = readInteger();
        }
    }

    /**
     * Reads and returns a long
     *
     * @return the 8 bytes merged into a long, according to the
     *         current byte ordering
     * @exception EOFException there were less than 8 bytes left in
     *            the file
     * @exception FormatException rethrow of IOExceptions encountered
     *            while reading the bytes for the long
     * @see #read(byte[])
     */
    public long readLong() throws EOFException, FormatException {
        return MoreMath.BuildLong(readBytes(8, false), MSBFirst);
    }

    /**
     * Reads and returns a float
     *
     * @return the 4 bytes merged into a float, according to the
     *         current byte ordering
     * @exception EOFException there were less than 4 bytes left in
     *            the file
     * @exception FormatException rethrow of IOExceptions encountered
     *            while reading the bytes for the float
     * @see #read(byte[])
     */
    public float readFloat() throws EOFException, FormatException {
        return Float.intBitsToFloat(readInteger());
    }

    public void readFloatArray(float vec[], int offset, int len)
            throws EOFException, FormatException {
        for (int i = 0; i < len; i++) {
            vec[offset++] = readFloat();
        }
    }

    /**
     * Reads and returns a double
     *
     * @return the 8 bytes merged into a double, according to the
     *         current byte ordering
     * @exception EOFException there were less than 8 bytes left in
     *            the file
     * @exception FormatException rethrow of IOExceptions encountered
     *            while reading the bytes for the short
     * @see #read(byte[])
     */
    public double readDouble() throws EOFException, FormatException {
        return Double.longBitsToDouble(readLong());
    }

    /**
     * Reads <code>length</code> bytes and returns a string composed
     * of the bytes cast to chars
     *
     * @param length the number of bytes to read into the string
     * @return the composed string
     * @exception EOFException there were less than
     *            <code>length</code> bytes left in the file
     * @exception FormatException rethrow of IOExceptions encountered
     *            while reading the bytes for the short
     */
    public String readFixedLengthString(int length) throws EOFException,
            FormatException {

        byte foo[] = readBytes(length, false);
        return new String(foo, 0, length);
    }

    /**
     * Reads a string until the specified delimiter or EOF is
     * encountered
     *
     * @param delim the end-of-string delimiter
     * @return the string that was read
     * @exception FormatException rethrow of IOExceptions from the
     *            read methods
     */
    public String readToDelimiter(char delim) throws FormatException {
        StringBuffer buildretval = new StringBuffer();
        char tmp;
        try {
            while ((tmp = readChar()) != delim)
                buildretval.append(tmp);
        } catch (EOFException e) {
            //allowable
        }
        return buildretval.toString();
    }

    /**
     * Makes sure that the file has been closed.
     *
     * @exception Throwable what it throws.
     */
    public void finalize() throws Throwable {
        close();
    }
}
