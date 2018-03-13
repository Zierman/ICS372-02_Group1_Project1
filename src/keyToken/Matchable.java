package keyToken;

public interface Matchable<MatchableType>
{
	public boolean matches(MatchableType valueToMatch);
}
