package andy.web.model;

public class Bible {
	private String book;
	private int chapter;
	private int verse;
	private String content;
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public int getVerse() {
		return verse;
	}
	public void setVerse(int verse) {
		this.verse = verse;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Bible => [book=" + book + ", chapter=" + chapter + ", verse=" + verse + ", content=" + content + "]";
	}


}
