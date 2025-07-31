package org.http4s
package headers

import cats.parse.Parser
import cats.parse.Parser.ignoreCase
import org.typelevel.ci.*

sealed abstract class Expect(val value: CIString) extends Product with Serializable

object Expect {
  case object Continue extends Expect(ci"100-continue")

  def parse(s: String): ParseResult[Expect] =
    ParseResult.fromParser(parser, "Invalid Expect header")(s)

  private[http4s] val parser: Parser[Expect] =
    ignoreCase("100-Continue").as(Continue)

  implicit val headerInstance: Header[Expect, Header.Single] =
    Header.createRendered(
      ci"Expect",
      _.value.toString,
      parse,
    )
}

