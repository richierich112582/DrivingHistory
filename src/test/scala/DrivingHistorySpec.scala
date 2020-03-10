import java.io.FileNotFoundException

import org.scalatest.funspec.AnyFunSpec

class DrivingHistorySpec extends AnyFunSpec {
  describe("A DrivingHistory") {
    describe("when main is invoked") {
      it("should throw an error if the input file is not found or provided in args") {
        assertThrows[UnknownError] {
          DrivingHistory.main(Array(null))
        }
        assertThrows[FileNotFoundException] {
          DrivingHistory.main(Array("blah"))
        }
      }
    }
  }
}
