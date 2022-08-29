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
public class SecondBadKey {
	private String str;
	private Integer num;
	@Override
	public int hashCode() {
		return str.hashCode() * num.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true; 
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecondBadKey other = (SecondBadKey) obj;
		return Objects.equals(num, other.num) && Objects.equals(str, other.str);
	}
	
}
