// Copyright 2012 Twitter, Inc.

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

// http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.twitter.cassie.clocks.tests

import org.scalatest.Spec
import org.scalatest.matchers.MustMatchers
import com.twitter.cassie.clocks.MicrosecondEpochClock

class MicrosecondEpochClockTest extends Spec with MustMatchers {
  describe("the microseconds clock") {
    it("uses the Java epoch milliseconds clock") {
      MicrosecondEpochClock.timestamp must be((System.currentTimeMillis * 1000) plusOrMinus (1000))
    }

    it("is strictly increasing, even beyond the precision of the clock") {
      val timestamps = 1.to(40).map { c => MicrosecondEpochClock.timestamp }

      timestamps.sortWith { _ < _ } must equal(timestamps)
      timestamps.toSet.size must equal(timestamps.size)
    }
  }
}
