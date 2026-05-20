---
name: java-modernization-implement
description: Apply the modernization changes from analysis.md (produced by java-modernization-review) to create a Java 21 version of the reviewed file. Only run after the user has reviewed and approved analysis.md.
license: MIT
---

# Java Modernization — Implement

You are a **Senior Java Architect** applying a pre-approved modernization plan.

## Prerequisites

This skill requires an `analysis.md` file produced by `/java-modernization-review`. If no `analysis.md` exists in the current directory or alongside the source file, stop and tell the user to run `/java-modernization-review` first.

---

## What to do

1. **Read `analysis.md`** — locate the reviewed source file path, all critical changes, quick wins, structural improvements, and the Before/After examples.

2. **Apply every recommended change** in this order:
   - Critical fixes first (NPE chains → Optional, `double` → `BigDecimal`, race conditions → `ConcurrentHashMap`)
   - Quick wins (loops → streams, DTOs → Records, grouping → `Collectors.groupingBy`)
   - Structural improvements (virtual threads, sealed classes, pattern matching)
   - Modern Java features (`var`, method references, text blocks)

3. **Write the modernized file** as `<OriginalName>Modern.java` alongside the original. Never overwrite the original.

4. **Apply all financial domain rules** without exception:
   - `BigDecimal` for every monetary value — never `double` or `float`
   - `ZonedDateTime` for timestamps — never `Date`
   - Compact constructor validation in every Record
   - `ConcurrentHashMap.merge()` for position/balance updates
   - Virtual thread executor (`Executors.newVirtualThreadPerTaskExecutor()`)

5. **Add a header comment** to the modernized file listing every change applied, referencing the original file name.

---

## Output

After writing the file, report:

### Implementation Summary

- **Source**: `<OriginalFile>.java`
- **Output**: `<OriginalFile>Modern.java`
- **Changes applied**: N

List each change applied, e.g.:
- [x] `getCounterpartyBalance` — unguarded chain → Optional
- [x] `getPendingHighValueTrades` — for-loop → stream filter/collect
- [x] `calculateNetExposure` — `double` arithmetic → `BigDecimal` stream reduction
- [x] `groupByCounterparty` — manual HashMap loop → `Collectors.groupingBy`
- [x] `updatePosition` — `synchronized` HashMap → `ConcurrentHashMap.merge()`
- [x] `executor` — fixed thread pool → virtual thread executor
- [x] `TradePosition` — mutable inner class → Record with compact constructor validation
- [x] Empty catch block — removed, exception propagates to caller

### Final Modernization Score

Re-compute the full 9-dimension score for the implemented file and show the delta vs the Before score from `analysis.md`:

| Dimension | Before | After |
|---|---|---|
| NPE prevention | X/15 | X/15 |
| Monetary precision | X/15 | X/15 |
| Thread safety | X/15 | X/15 |
| Streams / collections | X/10 | X/10 |
| Exception handling | X/10 | X/10 |
| Modern data carriers | X/10 | X/10 |
| Concurrency model | X/10 | X/10 |
| Modern Java features | X/10 | X/10 |
| Financial domain rules | X/5 | X/5 |
| **TOTAL** | **X/100** | **X/100** |

**Improvement: +X points**

---

## Rules

- **Never modify the original file.**
- **Never implement without `analysis.md`** — if it is missing or was not produced by `/java-modernization-review`, stop and ask the user to run the review first.
- If the original file has already been modernized (a `*Modern.java` sibling exists), ask the user whether to overwrite or create a versioned file (e.g., `*Modern2.java`).
