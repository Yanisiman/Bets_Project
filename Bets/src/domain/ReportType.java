package domain;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public enum ReportType {
	USER_BEHAVIOR,
	MALFUNCTION,
	OTHER,
}
