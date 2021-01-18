package d_2021_01_12

import config.MockkTest
import io.mockk.Answer
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

internal class Test: MockkTest() {

    @Test
    fun `테스트_1`() {

        // given
        val cnt = 3
        val map = arrayOf(
            arrayOf(5, 5, 4),
            arrayOf(3, 9, 1),
            arrayOf(3, 2, 7)
        )
        Assertions.assertEquals(20, solution(map, cnt))
    }

    @Test
    fun `테스트_2`() {
        val cnt = 5
        val map = arrayOf(
            arrayOf(3, 7, 2, 0, 1),
            arrayOf(2, 8, 0, 9, 1),
            arrayOf(1, 2, 1, 8, 1),
            arrayOf(9, 8, 9, 2, 0),
            arrayOf(3, 6, 5, 1, 5)
        )
        Assertions.assertEquals(19, solution(map, cnt))
    }

    @Test
    fun `테스트_3`() {
        val cnt = 7
        val map = arrayOf(
            arrayOf(9,0,5,1,1,5,3),
            arrayOf(4,1,2,1,6,5,3),
            arrayOf(0,7,6,1,6,8,5),
            arrayOf(1,1,7,8,3,2,3),
            arrayOf(9,4,0,7,6,4,1),
            arrayOf(5,8,3,2,4,8,3),
            arrayOf(7,4,8,4,8,3,4),
        )
        Assertions.assertEquals(36, solution(map, cnt))
    }
}