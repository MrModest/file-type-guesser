import java.io.File
import java.io.RandomAccessFile

class FileTypeChecker(private val knownFileTypes: Array<FileType>) {
    fun getFileTypes(file: File): Array<FileType> {
        val raFile = RandomAccessFile(file, "r")

        return knownFileTypes
            .sortedByDescending(FileType::priority)
            .filter { it.matches(raFile) }
            .toTypedArray()
    }

    fun getFileType(file: File): FileType {
        return getFileTypes(file)
            .firstOrNull()
            ?: FileType.unknown
    }
}

fun createFromInCodeFileType(): FileTypeChecker {
    return FileTypeChecker(getInCodeFileTypes())
}