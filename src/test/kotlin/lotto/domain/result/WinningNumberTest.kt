package lotto.domain.result

import lotto.domain.number.LottoTicket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class WinningNumberTest {
    @Test
    fun `당첨 번호는 6개의 중복되지 않은 숫자로 이뤄진다`() {
        val winningNumber = WinningNumber(LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)))
        assertThat(winningNumber.numbers.size).isEqualTo(6)
    }

    @ParameterizedTest
    @MethodSource("matchResultArgument")
    fun `당첨번호에 로또 티켓을 받아 번호 일치 개수를 확인할 수 있다`(ticket: LottoTicket, result: LottoTicketMatchResult) {
        val winningNumber = WinningNumber(LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)))
        assertThat(winningNumber.match(ticket)).isEqualTo(result)
    }

    companion object {
        @JvmStatic
        fun matchResultArgument(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(LottoTicket.from(listOf(1,2,3,4,5,6)), LottoTicketMatchResult(6)),
                Arguments.of(LottoTicket.from(listOf(1,2,3,4,5,7)), LottoTicketMatchResult(5)),
                Arguments.of(LottoTicket.from(listOf(1,2,3,4,7,8)), LottoTicketMatchResult(4)),
                Arguments.of(LottoTicket.from(listOf(1,2,3,7,8,9)), LottoTicketMatchResult(3)),
                Arguments.of(LottoTicket.from(listOf(1,2,7,8,9,10)), LottoTicketMatchResult(2)),
                Arguments.of(LottoTicket.from(listOf(1,7,8,9,10,11)), LottoTicketMatchResult(1)),
                Arguments.of(LottoTicket.from(listOf(7,8,9,10,11,12)), LottoTicketMatchResult(0))

            )
        }
    }
}
