package com.anoxymoron.tuples;

import java.util.ArrayList;

/**
 * StandardCard extends AbstractCard (flexibility for future Card types).
 *
 * StandardCard will have four properties, generally implemented as enums:
 *  - number (not an enum)
 *  - color
 *  - shape
 *  - texture
 *
 * StandardCard objects can be "simple" or "complex"
 *  - simple StandardCard objects have three options for their four properties
 *  - complex StandardCard objects have four options for their four properties
 *
 *  note: for three card sets, number ordinals 1 through 3 are used. For four card sets,
 *      number ordinals 0 through 3 are used. Reason: I'm being moderately fancy with xor
 *      to calculate the correct value for the third/fourth card.
 *
 *  TODO: add value checking to all constructors
 *
 * Created by jonathan on 12/22/17.
 */

public class StandardCard extends AbstractCard {
    private int number;
    private Color color;
    private Shape shape;
    private Texture texture;

    // construct random standard card (no params)
    public StandardCard(String type) {
        int max = 3;
        int min = type.equals("complex") ? 0 : 1;
        int range = (max - min) + 1;

        // returns a rand number 1-3 (if !complex) or 0-3 (if complex)
        int colorVal = (int)(Math.random() * range) + min;
        int shapeVal = (int)(Math.random() * range) + min;
        int textureVal = (int)(Math.random() * range) + min;

        number = (int)(Math.random() * range) + min;
        color = Color.values()[colorVal];
        shape = Shape.values()[shapeVal];
        texture = Texture.values()[textureVal];
    }

    // standard constructor - pass in params (use default values if params not specified)
    public StandardCard(int[] params) {
        number = params.length > 0 ? params[0] : 1;
        color = params.length > 1 ? Color.values()[params[1]] : Color.values()[0];
        shape = params.length > 2 ? Shape.values()[params[2]] : Shape.values()[0];
        texture = params.length > 3 ? Texture.values()[params[3]] : Texture.values()[0];
    }

    // constructor to create third card in a set (first and second cards are known)
    // note: assumes that card params are 1 through 3 (no 0 index params, since this is a three-card set)
    public StandardCard(StandardCard first, StandardCard second) {
        // being fancy: if the first/second values are the same, the third will be the same.
        //              if they're different, xor the first / second to find the third value
        int colorVal = first.getColor() == second.getColor() ?
                first.getColor().ordinal() :
                first.getColor().ordinal() ^ second.getColor().ordinal();
        int shapeVal = first.getShape() == second.getShape() ?
                first.getShape().ordinal() :
                first.getShape().ordinal() ^ second.getShape().ordinal();
        int textureVal = first.getTexture() == second.getTexture() ?
                first.getTexture().ordinal() :
                first.getTexture().ordinal() ^ second.getTexture().ordinal();

        number = first.getNumber() == second.getNumber() ? first.getNumber() : first.getNumber() ^ second.getNumber();
        color = Color.values()[colorVal];
        shape = Shape.values()[shapeVal];
        texture = Texture.values()[textureVal];
    }

    // constructor for fourth card in a set (first, second, and third cards are known)
    // note: card params are 0 through 4 (0 index params allowed, since this is a four-card set)
    public StandardCard(StandardCard first, StandardCard second, StandardCard third) {
        // same as above: if the first, second, and third values are the same, the fourth will be the same
        // otherwise, xor the first/second/third to find the fourth (this is why the "fourth" value has to
        // be stored in the 0th ordinal in the enum)
        int colorVal = (first.getColor() == second.getColor()) && (first.getColor() == third.getColor()) ?
                first.getColor().ordinal() :
                first.getColor().ordinal() ^ second.getColor().ordinal() ^ third.getColor().ordinal();
        int shapeVal = (first.getShape() == second.getShape()) && (first.getShape() == third.getShape()) ?
                first.getShape().ordinal() :
                first.getShape().ordinal() ^ second.getShape().ordinal() ^ third.getShape().ordinal();
        int textureVal = (first.getTexture() == second.getTexture()) && (first.getTexture() == third.getTexture()) ?
                first.getTexture().ordinal() :
                first.getTexture().ordinal() ^ second.getTexture().ordinal() ^ third.getTexture().ordinal();

        number = (first.getNumber() == second.getNumber()) && (first.getNumber() == third.getNumber()) ?
                first.getNumber() : first.getNumber() ^ second.getNumber() ^ third.getNumber();
        color = Color.values()[colorVal];
        shape = Shape.values()[shapeVal];
        texture = Texture.values()[textureVal];
    }

    // generate a list of all possible cards
    public static ArrayList<StandardCard> generateAllCards(String type) {
        ArrayList<StandardCard> allCards = new ArrayList<>();

        int max = 3;
        int min = type.equals("complex") ? 0 : 1;

        // four properties (number, color, shape, texture), so four loops
        for(int i = min; i <= max; i++) {
            for(int j = min; j <= max; j++) {
                for(int k = min; k <= max; k++) {
                    for(int m = min; m <= max; m++) {
                        allCards.add(new StandardCard(new int[] {i,j,k,m}));
                    }
                }
            }
        }

        return allCards;
    }

    // overriding hashCode() because... it needs to be overridden
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + this.getNumber();
        hash = hash * 31 + this.getColor().hashCode();
        hash = hash * 13 + this.getShape().hashCode();
        hash = hash * 41 + this.getTexture().hashCode();

        return hash;
    }

    // overriding equals() because... see above
    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof StandardCard))
            return false;

        StandardCard card = (StandardCard) o;

        return this.getNumber() == card.getNumber() &&
                this.getColor() == card.getColor() &&
                this.getShape() == card.getShape() &&
                this.getTexture() == card.getTexture();
    }

    // getters below - no setters, because encapsulation
    public int getNumber() {
        return number;
    }
    public Color getColor() {
        return color;
    }
    public Shape getShape() {
        return shape;
    }
    public Texture getTexture() {
        return texture;
    }
}
