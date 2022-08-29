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
public class GoodKey {
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
		if (this == obj)
			return true; 
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodKey other = (GoodKey) obj;
		return Objects.equals(num, other.num) && Objects.equals(str, other.str);
	}
	
}
