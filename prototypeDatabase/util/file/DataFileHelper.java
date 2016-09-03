package org.prototypeDatabase.util.file;

import org.prototypeDatabase.core.select.SelectSQL;
import org.prototypeDatabase.entity.Table;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Peyppicp on 2016/8/28.
 */
public class DataFileHelper {

    private Table table;

    public DataFileHelper() {
    }

    public DataFileHelper(Table table) {
        this.table = table;
    }

    public ByteBuffer selectFrom(SelectSQL selectSQL) throws IOException {
        File table_file = table.getTable_file();
        RandomAccessFile randomAccessFile = new RandomAccessFile(table_file, "rw");
        FileChannel channel = randomAccessFile.getChannel();
        long size = channel.size();
        return null;
    }

    public void insertTo(ByteBuffer byteBuffer) throws IOException {
        File table_file = table.getTable_file();
        RandomAccessFile randomAccessFile = new RandomAccessFile(table_file, "rw");
        FileChannel channel = randomAccessFile.getChannel();
        long position = channel.size();
        channel.write(byteBuffer, position);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
