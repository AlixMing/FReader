package com.reader.model;
/*
 * book_user model
 * mysql> desc book_user;
+----------+---------------------------+------+-----+---------+----------------+

| Field    | Type                      | Null | Key | Default | Extra          |
+----------+---------------------------+------+-----+---------+----------------+
| id       | int(11)                   | NO   | PRI | NULL    | auto_increment |
| userId   | int(11)                   | NO   | MUL | NULL    |                |
| bookId   | int(11)                   | YES  | MUL | NULL    |                |
| progress | int(10) unsigned zerofill | YES  |     | NULL    |                |
| isDelete | int(10) unsigned zerofill | YES  |     | NULL    |                |
| isLocal  | int(10) unsigned zerofill | YES  |     | NULL    |                |
| url      | varchar(255)              | YES  |     |         |                |
+----------+---------------------------+------+-----+---------+----------------+
 */
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class BookUsers extends Model<BookUsers> {
	public static final BookUsers me = new BookUsers();
	
	public Book getBook(){
		return Book.me.findById(getInt("bookId"));
	}
	/*
	public List<Book> getBooks(int userId){
		List<BookUsers> bookUsersList = BookUsers.me.find("select * from book_user where userId = ?", userId);
		List<Book> books = new ArrayList<Book>();
		for (BookUsers bookUsers : bookUsersList) {
			books.add(Book.me.findById(bookUsers.getInt("bookId")));
		}
		return books;
	}*/
}
