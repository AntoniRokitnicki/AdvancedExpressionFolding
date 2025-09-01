# Feature Idea: Adaptive Performance Guard

**Hook:** Plugin automatically suspends costly folding rules when the IDE lags.

**Why now?** Large projects report slowdowns; dynamic throttling keeps the plugin usable.

**Lift-Cost Score:** M

**Starter spec:**
- Measure folding computation time per rule.
- Disable or degrade rules exceeding thresholds and notify via tooltip.
- Provide a performance log accessible from settings.

