import java.lang.IllegalArgumentException;

public class Direction {
    private int vertical;
    private int horizontal;

    public Direction(){
        this(1,1);
    }

    public Direction(int dx, int dy){
        setDirection(dx, dy);
    }

    public void toggleVertical(){
        vertical *= -1;
    }

    public void toggleHorizontal(){
        horizontal *= -1;
    }

    public int getVerticalSign(){
        return vertical;
    }

    public int getHorizontalSign(){
        return horizontal;
    }
    
    // Model can set the direction
    public void setDirection(int dx, int dy) {
    	vertical = Integer.signum(dy);
    	horizontal = Integer.signum(dx);
    }

    public String getName(){
        String name = "";
        switch(vertical){
            case -1:
                name += "NORTH";
                break;
            case 1:
                name += "SOUTH";
                break;
            default:
                break;
        }
        switch(horizontal){
            case 1:
                name += "EAST";
                break;
            case -1:
                name += "WEST";
                break;
            default:
                if (name.equals(""))
                    throw new IllegalArgumentException("Direction not defined");
                break;
        }

        return name;
    }
}