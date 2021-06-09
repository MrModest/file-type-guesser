import filetypematchers.ExactFileTypeMatcher
import filetypematchers.FileTypeMatcher
import filetypematchers.FuzzyFileTypeMatcher
import filetypematchers.RangeFileTypeMatcher

fun exact(vararg magicNumbers: Int) = ExactFileTypeMatcher(magicNumbers.map(Int::toByte).toTypedArray())
fun fuzzy(vararg magicNumbers: Int?) = FuzzyFileTypeMatcher(magicNumbers.map { it?.toByte() }.toTypedArray())
fun range(fileTypeMatcher: FileTypeMatcher, maximumStartLocation: Long) = RangeFileTypeMatcher(fileTypeMatcher, maximumStartLocation)

fun getInCodeFileTypes(): Array<FileType> {
    return arrayOf(
        FileType("Bitmap", arrayOf("bmp"), exact(0x42, 0x4d)),
        FileType("Standard JPEG/JFIF file", arrayOf("jpg"), fuzzy(0xFF, 0xD8, 0xFF, 0xE0, null, null, 0x4A, 0x46, 0x49, 0x46, 0x00)),
        FileType("Portable Document Format", arrayOf("pdf"), range(exact(0x25, 0x50, 0x44, 0x46 ), 1019))
    )
}