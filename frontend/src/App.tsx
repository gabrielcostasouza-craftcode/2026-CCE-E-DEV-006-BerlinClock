import { useState, type SubmitEvent } from 'react'
import './App.css'
import { fetchBerlinTime, fetchDigitalTime } from './api/berlinClockApi'
import { BerlinClockDisplay } from './components/BerlinClockDisplay'

function App() {
  const [time, setTime] = useState('')
  const [rows, setRows] = useState<string[]>([])
  const [error, setError] = useState<string | null>(null)
  //digital
  const [berlinTime, setBerlinTime] = useState('')
  const [digitalTime, setDigitalTime] = useState('')
  const [decodedRows, setDecodedRows] = useState<string[]>([])
  const [decodeError, setDecodeError] = useState<string | null>(null)

  async function handleSubmit(event: SubmitEvent<HTMLFormElement>) {
    event.preventDefault()
    try {
      const clock = await fetchBerlinTime(time)
      setRows(clock.rows)
      setError(null)
    } catch (e) {
      setError((e as Error).message)
    }
  }

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
      <form onSubmit={handleSubmit}>
        <label htmlFor="time" style={{margin: '5px'}} >Digital time</label>
        <input
          id="time"
          value={time}
          placeholder="HH:mm:ss"
          onChange={(e) => setTime(e.target.value)}
        />
        <button type="submit">Convert</button>
      </form>
      {error && <p role="alert">{error}</p>}
      <BerlinClockDisplay rows={rows} />

      <form onSubmit={handleDecode}>
        <label htmlFor="berlinTime" style={{margin: '5px'}}>Berlin time</label>
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

export default App
