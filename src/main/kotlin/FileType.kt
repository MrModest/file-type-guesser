import filetypematchers.FileTypeMatcher
import java.io.RandomAccessFile

class FileType(
    val name: String,
    val extensions: Array<String>,
    private val fileTypeMatcher: FileTypeMatcher
) {
    companion object {
        val unknown = FileType("Unknown", emptyArray(), EmptyFileTypeMatcher())
    }

    fun matches(file: RandomAccessFile): Boolean = fileTypeMatcher.matches(file)

    private class EmptyFileTypeMatcher : FileTypeMatcher() {
        override fun matchesInternal(file: RandomAccessFile) = true
        override val priority = 0
    }

    val priority = fileTypeMatcher.priority
}