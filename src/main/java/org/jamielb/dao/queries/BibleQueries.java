package org.jamielb.dao.queries;

public class BibleQueries {

    private static final String VERSION_FIELDS = "v.version_code, v.version_name";
    private static final String BOOK_FIELDS = "b.id AS book_id, b.title AS book_title";
    private static final String TESTAMENT_FIELDS = "t.id AS testament_id, t.description AS testament_description";
    private static final String VERSE_FIELDS = "vs.id AS verse_id, vs.verse_text, vs.chapter_number, vs.verse_number";

    private BibleQueries() {
    }

    public static String getAllVersions() {
        return "SELECT %1$s FROM versions v".formatted(VERSION_FIELDS);
    }

    public static String getAllBooks() {
        return "SELECT %1$s, %2$s FROM books b INNER JOIN testaments t ON b.testament_id = t.id".formatted(BOOK_FIELDS, TESTAMENT_FIELDS);
    }

    public static String getVersionVerses() {
        return """
               SELECT %1$s, %2$s, %3$s, %4$s
               FROM verses vs
               INNER JOIN versions v ON vs.version_code = v.version_code
               INNER JOIN books b ON vs.book_id = b.id
               INNER JOIN testaments t ON b.testament_id = t.id
               WHERE vs.version_code = ?
               """.formatted(VERSE_FIELDS, VERSION_FIELDS, BOOK_FIELDS, TESTAMENT_FIELDS);
    }

    public static String getVerse() {
        return getVersionVerses() + " AND vs.book_id = ? AND vs.chapter_number = ? AND vs.verse_number = ?";
    }

    public static String getBookChapters() {
        return "SELECT DISTINCT chapter_number FROM verses WHERE version_code = ? AND book_id = ? ORDER BY chapter_number";
    }

    public static String getChapterVerses() {
        return "SELECT verse_number FROM verses WHERE version_code = ? AND book_id = ? AND chapter_number = ? ORDER BY verse_number";
    }

    public static String getNextVerse() {
        return """
                WITH first_result AS (
                    SELECT id, version_code, book_id, chapter_number, verse_number
                    FROM verses
                    WHERE version_code = ?
                    AND book_id = ?
                    AND chapter_number = ?
                    AND verse_number > ?
                    UNION
                    SELECT id, version_code, book_id, chapter_number, verse_number
                    FROM verses
                    WHERE version_code = ?
                    AND book_id = ?
                    AND chapter_number > ?
                    UNION
                    SELECT id, version_code, book_id, chapter_number, verse_number
                    FROM verses
                    WHERE version_code = ?
                    AND book_id > ?
                    ORDER BY book_id, chapter_number, verse_number
                    LIMIT 1
                )
                SELECT %1$s, %2$s, %3$s, %4$s
                FROM verses vs
                INNER JOIN first_result fr ON vs.id = fr.id
                INNER JOIN versions v ON vs.version_code = v.version_code
                INNER JOIN books b ON vs.book_id = b.id
                INNER JOIN testaments t ON b.testament_id = t.id;
               """.formatted(VERSE_FIELDS, VERSION_FIELDS, BOOK_FIELDS, TESTAMENT_FIELDS);
    }

    public static String getPreviousVerse() {
        return """
                WITH last_result AS (
                    SELECT id, version_code, book_id, chapter_number, verse_number
                    FROM verses
                    WHERE version_code = ?
                    AND book_id = ?
                    AND chapter_number = ?
                    AND verse_number < ?
                    UNION
                    SELECT id, version_code, book_id, chapter_number, verse_number
                    FROM verses
                    WHERE version_code = ?
                    AND book_id = ?
                    AND chapter_number < ?
                    UNION
                    SELECT id, version_code, book_id, chapter_number, verse_number
                    FROM verses
                    WHERE version_code = ?
                    AND book_id < ?
                    ORDER BY book_id DESC, chapter_number DESC, verse_number DESC
                    LIMIT 1
                )
                SELECT %1$s, %2$s, %3$s, %4$s
                FROM verses vs
                INNER JOIN last_result lr ON vs.id = lr.id
                INNER JOIN versions v ON vs.version_code = v.version_code
                INNER JOIN books b ON vs.book_id = b.id
                INNER JOIN testaments t ON b.testament_id = t.id;
               """.formatted(VERSE_FIELDS, VERSION_FIELDS, BOOK_FIELDS, TESTAMENT_FIELDS);
    }

}
