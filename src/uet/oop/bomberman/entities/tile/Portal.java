package uet.oop.bomberman.entities.tile;

import sun.util.resources.cldr.br.CurrencyNames_br;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Tile {
	protected Board _board;
	public Portal(int x, int y, Sprite sprite,Board board) {

		super(x, y, sprite);
		_board = board;
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý khi Bomber đi vào
		if(e instanceof Bomber)
		{
				if(_board.detectNoEnemies())
					_board.nextLevel();
		}
		return true;
	}

}
