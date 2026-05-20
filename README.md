# Java Claude Skills — Java 8 → Java 21 Modernization for Claude Code

> **AI-powered Java code modernization skill for [Claude Code](https://claude.ai/code).**  
> Analyzes legacy Java, generates a prioritized upgrade report, scores it 1–100, and applies every change — all inside your editor.

![Java](https://img.shields.io/badge/Java-8%20→%2021-orange?logo=openjdk)
![Claude Code](https://img.shields.io/badge/Claude%20Code-Skill-blueviolet?logo=anthropic)
![License](https://img.shields.io/badge/License-MIT-green)
![Financial Systems](https://img.shields.io/badge/Domain-Financial%20Systems-blue)

---

## Why this skill?

Migrating Java 8 code to Java 21 is high-value but tedious — every codebase has the same problems:

| Problem | Impact |
|---|---|
| `double` for monetary values | Silent precision loss on every FX calculation |
| Unguarded chained `.get()` calls | NPE crashes in production settlement flows |
| `synchronized` HashMap | Race conditions on position updates under load |
| Fixed thread pools | Bottleneck at 200–500 concurrent trades |
| Boilerplate DTOs with 60+ lines | Noise that obscures domain logic |

This skill acts as a **Senior Java Architect** in your Claude Code session. It knows every Java feature from 8 through 21, enforces financial-domain safety rules, and produces code you can ship — not just advice.

---

## Skills

### 1. `java-modernization-review`

Analyzes legacy Java code and produces a full modernization report saved to `analysis.md`. **Stops before touching any code** — you review first.

**Covers 7 upgrade priority tracks:**

| Priority | Track | Java Version |
|---|---|---|
| 1 | Streams, Optional, method references | 8+ |
| 2 | `var`, helpful NPE messages, built-in HTTP client | 9–11 |
| 3 | Records, sealed classes, pattern matching, text blocks | 12–16 |
| 4 | Virtual threads, structured concurrency, lock-free atomics | 17–21 |
| 5 | String templates, unnamed classes, type inference | 18–21 |
| 6 | Legacy removal — `Date`→`java.time`, reactive→virtual threads | 8+ |
| 7 | Module system (`module-info.java`) | 9+ |

**Every review produces:**
- Critical issues (NPE chains, thread safety, `double` money) flagged first
- Quick wins checklist with occurrence counts
- Before/After code examples for the top 3 changes
- A 4-phase modernization roadmap
- A testing strategy

---

### 2. `java-modernization-implement`

Reads `analysis.md` (produced by the review skill), applies every recommended change, and writes a `*Modern.java` file alongside the original. The original is **never modified**.

---

## Modernization Score (1–100)

Every review and implementation produces a **Before / After score** across 9 dimensions:

| Dimension | Max Points |
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

**Real result on the included `TradeProcessor.java` example:**

```
| Dimension              | Before  | After  |
|------------------------|---------|--------|
| NPE prevention         |  0/15   | 15/15  |
| Monetary precision     |  0/15   | 15/15  |
| Thread safety          |  0/15   | 15/15  |
| Streams / collections  |  0/10   | 10/10  |
| Exception handling     |  0/10   |  6/10  |
| Modern data carriers   |  0/10   | 10/10  |
| Concurrency model      |  2/10   | 10/10  |
| Modern Java features   |  1/10   |  8/10  |
| Financial domain rules |  0/5    |  5/5   |
| TOTAL                  |  3/100  | 95/100 |

Improvement: +92 points
```

---

## Two-Step Workflow

```
Step 1 — Review                          Step 2 — Implement
───────────────────                      ──────────────────────────────
/java-modernization-review    ────────►  /java-modernization-implement

Analyzes the file.                       Reads analysis.md.
Generates analysis.md.                   Applies every change.
Stops. Waits for approval.               Writes *Modern.java alongside original.
                                         Reports final score + delta.
```

The original file is **never modified**.

---

## Install (Claude Code)

**Step 1 — Add this repo as a marketplace:**
```
/plugin marketplace add Santoshrt999/Java-Claude-Skills
```

**Step 2 — Install both skills:**
```
/plugin install java-claude-skills@java-modernization-review
/plugin install java-claude-skills@java-modernization-implement
```

**Step 3 — Reload plugins:**
```
/reload-plugins
```

---

## Usage

**Run the review:**
```
/java-modernization-review

Review src/main/java/com/yourcompany/TradeProcessor.java
```

Opens `analysis.md` with the full report and score. Review it, then:

**Apply the changes:**
```
/java-modernization-implement
```

Writes `TradeProcessorModern.java` and prints the final score with improvement delta.

---

## Financial Domain Rules (always enforced)

- `BigDecimal` for all monetary amounts — never `double` or `float`
- `ZonedDateTime` for timestamps — never `Date`
- `ConcurrentHashMap.merge()` for atomic position/balance updates
- Immutable domain objects via Records with compact constructor validation
- Idempotent message handlers for settlement and clearing
- Audit trail via event sourcing

---

## Example

Side-by-side comparison included in this repo:

| File | Description |
|---|---|
| [`TradeProcessor.java`](examples/src/main/java/com/fintech/trading/TradeProcessor.java) | Legacy Java 8 — NPE chains, `double` money, `synchronized` HashMap, swallowed exceptions, rigid DTO |
| [`TradeProcessorModern.java`](examples/src/main/java/com/fintech/trading/TradeProcessorModern.java) | Java 21 — Optional, streams, `ConcurrentHashMap.merge()`, virtual threads, Record, BigDecimal |
| [`analysis.md`](examples/src/main/java/com/fintech/trading/analysis.md) | Full review report with checklist and final score |

---

## Who is this for?

- Java developers modernizing legacy **financial systems**, trading platforms, or microservices to Java 21
- Teams migrating from **Java 8, 11, or 17** to Java 21 LTS
- Anyone using **Claude Code** who wants AI-assisted code review with measurable results
- Engineers working with **Kafka, Solace, RabbitMQ** messaging systems on legacy threading models

---

## License

MIT — free to use, modify, and share.

---

*Built with [Claude Code](https://claude.ai/code) · Skill format compatible with Claude Code plugin marketplace*
