package buildings;

public class Utils {
    static Space[] sortByArea(Space[] spaces){
        Space swapBuf;
        for(int i = spaces.length - 1; i > 0; i--)
        {
            for(int j = 0; j < i; j++)
            {
                if(spaces[j].getArea() < spaces[j+1].getArea())
                {
                    swapBuf = spaces[j];
                    spaces[j] = spaces[j+1];
                    spaces[j+1] = swapBuf;
                }
            }
        }
        return spaces;
    }
}
