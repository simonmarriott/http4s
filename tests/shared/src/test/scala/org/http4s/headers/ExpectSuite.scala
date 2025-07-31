package org.http4s
package headers

import org.http4s.headers.Expect.Continue
import org.http4s.laws.discipline.arbitrary.*

class ExpectSuite extends HeaderLaws {
  checkAll("Expect", headerLaws[Expect])

  test("Expect parses 100-Continue") {
    assertEquals(Expect.parse("100-Continue"), Right(Continue))
  }

  test("Expect parses 100-continue") {
    assertEquals(Expect.parse("100-continue"), Right(Continue))
  }

}
