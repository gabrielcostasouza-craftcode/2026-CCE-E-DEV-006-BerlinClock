const LAMPS = {
  Y: { colour: 'yellow', label: 'Y on' },
  R: { colour: 'red', label: 'R on' },
  O: { colour: 'off', label: 'off' },
} as const

export type LampSymbol = keyof typeof LAMPS

type LampProps = {
  symbol: string
}

export function Lamp({ symbol }: LampProps) {
  const lamp = LAMPS[symbol as LampSymbol] ?? LAMPS.O
  return (
    <span
      role="img"
      aria-label={lamp.label}
      className={`lamp lamp--${lamp.colour}`}
    />
  )
}
