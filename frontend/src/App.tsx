import { useState, type SubmitEvent } from 'react'
import './App.css'
import { fetchBerlinTime } from './api/berlinClockApi'
import { BerlinClockDisplay } from './components/BerlinClockDisplay'

function App() {
  const [time, setTime] = useState('')
  const [rows, setRows] = useState<string[]>([])
  const [error, setError] = useState<string | null>(null)

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

  return (
    <>
      <form onSubmit={handleSubmit}>
        <label htmlFor="time" style={{margin: '5px' }} >Digital time</label>
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
    </>
  )
}

export default App
