import org.junit.Before
import org.junit.Test
import kotlin.test.*

class ChatServiceTest {

    @Before
    fun beforeTest() {
        ChatService.prepareForTests()
    }

    @Test
    fun newChatAndAddMessage() {
        val result = ChatService.letsChat(12, 18, "Привет, Серега.")
        assertEquals(1, result)
    }

    @Test
    fun chatExists() {
        ChatService.letsChat(12, 18, "Привет, Серега.")
        val result = ChatService.checkChatIsExists(12,18)
        assertEquals(1, result)
    }

    @Test
    fun chatNotExists() {
        ChatService.letsChat(12, 18, "Привет, Серега.")
        val result = ChatService.checkChatIsExists(12,21)
        assertEquals(0, result)
    }

    @Test(expected = ChatNotFoundException::class)
    fun shouldThrow() {
        ChatService.editMessage(4,4,"New message")
    }

    @Test
    fun getChats() {
        val user = User(12, "Я")
        val sergey = User(18, "Сергей")
        ChatService.userList = mutableListOf(user, sergey)
        ChatService.letsChat(12, 18, "Привет, Серега.")
        val result = ChatService.getChats(12)
        assertNotNull(result)
    }

    @Test
    fun editMessage() {
        ChatService.letsChat(12, 18, "Привет, Серега.")
        val result = ChatService.editMessage(1, 1, "Серега, привет.")
        assertTrue(result)
    }

    @Test
    fun deleteMessage() {
        ChatService.letsChat(12, 18, "Привет, Серега.")
        ChatService.letsChat(12, 18, "Как дела?")
        val result = ChatService.deleteMessage(1, 2)
        assertTrue(result)
    }

}