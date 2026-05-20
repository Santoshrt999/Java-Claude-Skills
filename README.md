# Java-Claude-Skills

Collection of Claude Code skills for Java development, focused on modernization and financial systems review.

---

## Skills

### `java-modernization-review`

Reviews legacy Java code (Java 8–11) and produces structured recommendations for modernizing to Java 17–21. Specialized for financial systems: trading platforms, settlement/clearing, risk engines, and message-driven microservices.

**Covers:**
- `for` loops → Stream API
- NPE prevention via `Optional` and `@NonNull`/`@Nullable`
- `double`/`float` → `BigDecimal` for all monetary values
- `@Queue`/`@Solace` threading → Kafka, virtual threads (Java 21)
- DTOs → Java Records; type hierarchies → Sealed classes
- `synchronized` blocks → `ConcurrentHashMap`, `AtomicReference`
- Saga pattern for distributed transaction failures

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

In Claude Code, invoke the skill on any Java file:

```
/java-modernization-review

Review examples/legacy/TradeProcessor.java
```

Or point it at a real codebase file:

```
/java-modernization-review

Review src/main/java/com/fintech/TradeProcessor.java
```

---

## Example Output

See [`examples/legacy/TradeProcessor.java`](examples/legacy/TradeProcessor.java) for a sample legacy file containing the patterns this skill detects:
- Unguarded chained access (NPE risk)
- `double` arithmetic on monetary values
- Manual `for`-loop grouping
- `synchronized` position updates
- Empty `catch (Exception e)` blocks
- Rigid multi-field DTO (Record candidate)

---

## License

MIT
