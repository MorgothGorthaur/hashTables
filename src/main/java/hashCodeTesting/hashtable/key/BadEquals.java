package hashCodeTesting.hashtable.key;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BadEquals {
	private String str;
	private Integer num;
	@Override
	public int hashCode() {
	    int hash = 7;
        hash = 31 * hash + (int) num;
        hash = 31 * hash + (str == null ? 0 : str.hashCode());
	    return hash;
	}
	@Override
	public boolean equals(Object obj) {
		return false;
	}
}
