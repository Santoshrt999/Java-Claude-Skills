# Java-Claude-Skills

Collection of Claude Code skills for Java development, focused on modernization and financial systems review.

---

## Skills

### `java-modernization-review`

A Senior Java Architect skill that reviews legacy Java code (Java 8+) and produces a complete, prioritized modernization report targeting Java 21 LTS. Covers every Java feature release from Java 9 through 21, with deep specialization in financial systems: trading platforms, settlement/clearing, risk engines, and message-driven microservices.

#### What it does

**Analyzes 7 upgrade priority tracks:**

| Priority | Track |
|---|---|
| 1 | Streams, Optional, method references (Java 8+) |
| 2 | `var`, helpful NPE messages, built-in HTTP client (Java 9–11) |
| 3 | Records, sealed classes, pattern matching, text blocks (Java 12–16) |
| 4 | Virtual threads, structured concurrency, lock-free atomics (Java 17–21) |
| 5 | String templates, unnamed classes, type inference (Java 18–21) |
| 6 | Legacy deprecation — `Date`→`java.time`, `StringBuffer`→`StringBuilder`, reactive→virtual threads |
| 7 | Module system (`module-info.java`) |

**For every reviewed file it produces:**
- Critical issues (NPE chains, thread safety violations, `double` money arithmetic) flagged first
- Quick wins checklist (loops, nulls, `instanceof`, DTOs) with occurrence counts
- Structural improvements (sealed classes, virtual thread conversion points, concurrent collections)
- Before/After code examples for the top 3 highest-impact changes
- A 4-phase modernization roadmap
- A testing strategy for refactored code

#### Modernization Score (1–100)

Every review ends with a **Before / After score** and an **improvement delta**, computed across 9 dimensions:

| Dimension | Max |
|---|---|
| NPE prevention | 15 |
| Monetary precision (`BigDecimal`) | 15 |
| Thread safety | 15 |
| Streams & collections | 10 |
| Exception handling | 10 |
| Modern data carriers (Records) | 10 |
| Concurrency model (virtual threads) | 10 |
| Modern Java features | 10 |
| Financial domain rules | 5 |
| **Total** | **100** |

Example output:

```
| Dimension              | Before | After  |
|------------------------|--------|--------|
| NPE prevention         |  0/15  | 15/15  |
| Monetary precision     |  0/15  | 11/15  |
| Thread safety          |  0/15  | 15/15  |
| ...                    |  ...   |  ...   |
| TOTAL                  |  3/100 | 89/100 |
Improvement: +86 points
```

#### Financial Domain Rules (always enforced)
- `BigDecimal` for all monetary values — never `double` or `float`
- `ZonedDateTime` for timestamps — never `Date`
- Atomic position updates, serializable ledger writes
- Immutable domain objects via Records
- Idempotent message handlers for settlement/clearing

---

## Install (Claude Code)

**Step 1 — Add this repo as a marketplace:**
```
/plugin marketplace add Santoshrt999/Java-Claude-Skills
```

**Step 2 — Install the skill:**
```
/plugin install java-claude-skills@java-modernization-review
```

**Step 3 — Reload plugins:**
```
/reload-plugins
```

The skill is now available in all your projects as `/java-modernization-review`.

---

## Usage

Invoke the skill on any Java file:

```
/java-modernization-review

Review src/main/java/com/yourcompany/TradeProcessor.java
```

---

## Example

This repo includes a side-by-side example:

| File | Description |
|---|---|
| [`TradeProcessor.java`](examples/src/main/java/com/fintech/trading/TradeProcessor.java) | Legacy Java 8 — NPE chains, `double` money, `synchronized` HashMap, empty catch, rigid DTO |
| [`TradeProcessorModern.java`](examples/src/main/java/com/fintech/trading/TradeProcessorModern.java) | Java 21 — Optional, streams, `ConcurrentHashMap.merge()`, virtual threads, Record, BigDecimal |

Score improvement on this example: **3/100 → 89/100 (+86 points)**

---

## License

MIT
