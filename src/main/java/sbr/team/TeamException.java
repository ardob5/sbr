package sbr.team;

public class TeamException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TeamException(Long id) {
		super("Team" + id + "not found");
	}
}
