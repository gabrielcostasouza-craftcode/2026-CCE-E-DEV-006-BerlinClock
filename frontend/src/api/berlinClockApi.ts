export type ClockResponse = {
  digitalTime: string
  berlinTime: string
  rows: string[]
}

export function fetchBerlinTime(localtime: string): Promise<ClockResponse> {
  return get(`/api/to-berlin-time?${new URLSearchParams({ localtime })}`)
}

async function get(url: string): Promise<ClockResponse> {
  const response = await fetch(url)
  if (response.ok) return response.json()

  const problem = await response.json().catch(() => null)
  throw new Error(problem?.error ?? `Request failed with status ${response.status}`)
}
