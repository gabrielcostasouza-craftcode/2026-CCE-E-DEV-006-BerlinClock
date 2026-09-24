import { useState, type SubmitEvent } from 'react'
import { fetchDigitalTime } from '../api/berlinClockApi'
import { BerlinClockDisplay } from './BerlinClockDisplay'

export function BerlinToDigital() {
  const [berlinTime, setBerlinTime] = useState('')
  const [digitalTime, setDigitalTime] = useState('')
  const [decodedRows, setDecodedRows] = useState<string[]>([])
  const [decodeError, setDecodeError] = useState<string | null>(null)

  async function handleDecode(event: SubmitEvent<HTMLFormElement>) {
    event.preventDefault()
    try {
      const clock = await fetchDigitalTime(berlinTime)
      setDigitalTime(clock.digitalTime)
      setDecodedRows(clock.rows)
      setDecodeError(null)
    } catch (e) {
      setDecodeError((e as Error).message)
    }
  }

  return (
    <>
      <form onSubmit={handleDecode}>
        <label htmlFor="berlinTime" style={{ margin: '5px' }}>Berlin time</label>
        <input
          id="berlinTime"
          value={berlinTime}
          placeholder="YRRROROOOYYRYYRYYRYOOOOO"
          onChange={(e) => setBerlinTime(e.target.value)}
        />
        <button type="submit">Convert</button>
      </form>
      {decodeError && <p role="alert">{decodeError}</p>}
      {digitalTime && <p>{digitalTime}</p>}
      <BerlinClockDisplay rows={decodedRows} />
    </>
  )
}
