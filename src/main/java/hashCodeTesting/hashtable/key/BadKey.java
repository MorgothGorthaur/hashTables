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
public class BadKey {
	private String str;
	private Integer num;
	@Override
	public int hashCode() {
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BadKey other = (BadKey) obj;
		return Objects.equals(num, other.num) && Objects.equals(str, other.str);
	}
	
}
