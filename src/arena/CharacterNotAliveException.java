package arena;

// Наследование от Exception делает это исключение "Проверяемым" (Checked)
public class CharacterNotAliveException extends Exception {

	// Создаем конструктор, который вызывает конструктор родителя (Exception)
	public CharacterNotAliveException(String message) {
		super(message);
	}
}