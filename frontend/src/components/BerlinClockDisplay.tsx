import { Lamp } from './Lamp'

const ROW_NAMES = ['seconds', 'five hours', 'single hours', 'five minutes', 'single minutes']
type BerlinClockDisplayProps = {
  rows: string[]
}

export function BerlinClockDisplay({ rows }: BerlinClockDisplayProps) {
  return (
    <div className="bc">
      {rows.map((row, rowIndex) => (
          <div key={ROW_NAMES[rowIndex]} role="group" aria-label={ROW_NAMES[rowIndex]} className="bc-row">
          {[...row].map((symbol, lampIndex) => (
            <Lamp key={lampIndex} symbol={symbol} />
          ))}
        </div>
      ))}
    </div>
  )
}
