export type ClockResponse = {
  digitalTime: string
  berlinTime: string
  rows: string[]
}

export function fetchBerlinTime(time: string): Promise<ClockResponse> {
  throw new Error('not implemented ')
}

