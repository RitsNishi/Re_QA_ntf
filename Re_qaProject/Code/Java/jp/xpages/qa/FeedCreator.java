package jp.xpages.qa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

public class FeedCreator {
	private String feedType;
	private String title;
	private String link;
	private String description;
	private List<SyndEntry> entries;

	/**
	 * �f�t�H���g�R���X�g���N�^�B�uRSS 2.0�v�`����Feed�𐶐����܂��B
	 */
	public FeedCreator() {
		this("rss_2.0");
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param feedType
	 *            �@�t�B�[�h�̌`�� "rss_1.0" "rss_2.0" "rss_0.9" "rss_0.92" "atom_1.0"
	 *            "atom_0.3"
	 */
	public FeedCreator(String feedType) {
		this.feedType = feedType;
		entries = new ArrayList<SyndEntry>();
	}

	/**
	 * �t�B�[�h�̌`�����w�肵�܂��B
	 * 
	 * @param feedType
	 */
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	/**
	 * �t�B�[�h�̃^�C�g�����w�肵�܂��B
	 * 
	 * @param title
	 */
	public void setFeedTitle(String title) {
		this.title = title;
	}

	/**
	 * �z�M���T�C�g��URL���w�肵�܂��B
	 * 
	 * @param link
	 */
	public void setFeedLink(String link) {
		this.link = link;
	}

	/**
	 * �t�B�[�h�̊T�v���w�肵�܂��B
	 * 
	 * @param description
	 */
	public void setFeedDescription(String description) {
		this.description = description;
	}

	/**
	 * �e�L�X�g�`���̏ڍ׃G���g���[���w�肵�܂��B
	 * 
	 * @param title
	 * @param link
	 * @param value
	 * @param pubDate
	 */
	public void setTextEntry(String guid, String title, String link, String value,
			Date pubDate) {
		SyndEntry entry;
		SyndContent description;

		entry = new SyndEntryImpl();
		entry.setTitle(title);
		entry.setLink(link);
		entry.setPublishedDate(pubDate);
		
		entry.setUri(guid);

		description = new SyndContentImpl();
		description.setType("text/plain");
		description.setValue(value);

		entry.setDescription(description);
		entries.add(entry);
	}

	/**
	 * �w�肳�ꂽ�`���Ńt�B�[�h���o�͂��܂��B ���̃T���v���ł͕W���o�͂ɕ�����Ƃ��ďo�͂��Ă��܂��B
	 */
	public String createFeed() {
		System.out.println("1");

		SyndFeed feed = (SyndFeed) new SyndFeedImpl();
		feed.setFeedType(feedType);
		feed.setTitle(title);
		feed.setLink(link);
		feed.setDescription(description);

		feed.setEntries(entries);

		SyndFeedOutput output = new SyndFeedOutput();
		String feedText = "";
		try {
			feedText = output.outputString(feed);

		} catch (FeedException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		return feedText;

	}
}
