package filetypematchers

import java.io.RandomAccessFile

abstract class FileTypeMatcher {
    fun matches(file: RandomAccessFile, resetPosition: Boolean = true): Boolean {
        if (file.filePointer != 0L && resetPosition){
            file.seek(0)
        }

        return matchesInternal(file)
    }

    protected abstract fun matchesInternal(file: RandomAccessFile): Boolean

    abstract val priority: Int
}