// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

/**
 * Mailtemplate generated by hbm2java
 */
public class Mailtemplate implements java.io.Serializable {

	private Integer idTemplate;
	private String description;
	private String subject;
	private String html;

	public Mailtemplate() {
	}

	public Mailtemplate(String description, String subject, String html) {
		this.description = description;
		this.subject = subject;
		this.html = html;
	}

	public Integer getIdTemplate() {
		return this.idTemplate;
	}

	public void setIdTemplate(Integer idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
