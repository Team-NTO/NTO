package d_2021_01_19

import config.MockkTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Test: MockkTest() {

    @Test
    fun `테스트 케이스 1`() {
        // given

        val N = 4 // 역의 개수
        val commands = listOf(
            Command("type", "a" ,1),
            Command("type", "b" ,2),
            Command("type", "c" ,3),
            Command("undo", "3" ,5),
        )

        Assertions.assertEquals("a", solution(commands))
    }

    @Test
    fun `테스트 케이스 2`() {
        // given

        val N = 4 // 역의 개수
        val commands = listOf(
            Command("type", "a" ,1),
            Command("type", "b" ,2),
            Command("type", "c" ,3),
            Command("undo", "20" ,10),
        )

        Assertions.assertEquals("", solution(commands))
    }

    @Test
    fun `테스트 케이스 3`() {
        // given

        val N = 4 // 역의 개수
        val commands = listOf(
            Command("type", "a" ,1),
            Command("type", "b" ,2),
            Command("undo", "2" ,3),
            Command("undo", "2" ,4),
        )

        Assertions.assertEquals("a", solution(commands))
    }

    @Test
    fun `테스트 케이스 4`() {
        // given

        val N = 16 // 역의 개수
        val commands = listOf(
            Command("type", "a", 1),
            Command("type", "b", 2),
            Command("type", "c", 3),
            Command("type", "d", 4),
            Command("undo", "2", 5),
            Command("undo", "1", 6),
            Command("undo", "4", 7),
            Command("undo", "2", 8),
            Command("type", "e", 9),
            Command("undo", "3", 10),
            Command("type", "f", 11),
            Command("undo", "1", 12),
            Command("undo", "3", 13),
            Command("type", "g", 14),
            Command("undo", "6", 15),
            Command("undo", "4", 16),
        )

        Assertions.assertEquals("abcdf", solution(commands))
    }
}
