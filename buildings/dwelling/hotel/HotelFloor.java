package buildings.dwelling.hotel;

import buildings.interfaces.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor {
    private static final int DEFAULT_STARS = 1;
    private int stars;

    public HotelFloor(int spacesQuantity){
        super(spacesQuantity);
        this.stars = DEFAULT_STARS;
    }

    public HotelFloor(Space[] spaces) {
        super(spaces);
        this.stars = DEFAULT_STARS;
    }

    public int getStars() {
        return stars;
    }

    public float getCoef(){
        float coef = stars*0.25f;
        if(stars > 2){
            coef += 0.25f;
        }
        return coef;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("HotelBuilding Floor (");
        builder.append(stars).append(getSize());
        for (Space space : getSpaces()) {
            builder.append(", ").append(space.toString());
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj instanceof HotelFloor){
            if(((HotelFloor) obj).getSize() == this.getSize()){
                for(int i = 0; i < getSize(); i++){
                    if(!((HotelFloor) obj).getSpace(i).equals(this.getSpace(i))){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getSize() ^ getStars();
        for(Space space : getSpaces()){
            hash ^= space.hashCode();
        }
        return hash;
    }
}
