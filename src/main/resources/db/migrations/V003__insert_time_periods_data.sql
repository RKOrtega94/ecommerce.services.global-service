-- Fix UNION type mismatch by casting literals to time_period_type
WITH constants AS (SELECT 5                          AS max_years_value,
                          'YEARLY'::time_period_type AS yearly_period)
INSERT
INTO TIME_PERIODS (VALUE, PERIOD_TYPE, LABEL, description)
SELECT 1, 'WEEKLY'::time_period_type, 'ONE_WEEK', 'One week'
UNION ALL
SELECT 1, 'MONTHLY'::time_period_type, 'ONE_MONTH', 'One month'
UNION ALL
SELECT 1, (SELECT yearly_period FROM constants), 'ONE_YEAR', 'One year'
UNION ALL
SELECT 2, (SELECT yearly_period FROM constants), 'TWO_YEARS', 'Two years'
UNION ALL
SELECT 3, (SELECT yearly_period FROM constants), 'THREE_YEARS', 'Three years'
UNION ALL
SELECT 4, (SELECT yearly_period FROM constants), 'FOUR_YEARS', 'Four years'
UNION ALL
SELECT (SELECT max_years_value FROM constants), (SELECT yearly_period FROM constants), 'FIVE_YEARS', 'Five years';
