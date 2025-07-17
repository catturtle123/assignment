package contest.assignment.global.feign

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.ToString

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class GPTErrorResponse(
    private val errorCode: String,
    private val errorMessage: String
) {
}