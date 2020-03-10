import java.io.FileNotFoundException

import scala.io.{BufferedSource, Source}

object DrivingHistory extends App {

  var bufferedSource: BufferedSource = null

  try {

    if (!this.args.isEmpty) {
      bufferedSource = Source.fromFile(this.args(0))

      val report = DrivingHistoryReportGenerator(bufferedSource.getLines().toList, 5, 100)
      report.generateReport.foreach(println)

    }

  } catch {

    case e: FileNotFoundException => throw new FileNotFoundException("File not found: " + e.getMessage)
    case unknown => throw new UnknownError("Got an unknown error: " + unknown.getMessage)

  } finally {

    if (bufferedSource != null) {
      bufferedSource.close()
    }
  }
}
