package filetypematchers

import java.io.RandomAccessFile

class RangeFileTypeMatcher(
    private val matcher: FileTypeMatcher,
    private val maximumStartLocation: Long) : FileTypeMatcher() {

    override fun matchesInternal(file: RandomAccessFile): Boolean {
        for (i in 0 until maximumStartLocation){
            file.seek(i)
            if (matcher.matches(file, resetPosition = false)){
                return true
            }
        }

        return false
    }

    override val priority: Int
        get() = matcher.priority
}