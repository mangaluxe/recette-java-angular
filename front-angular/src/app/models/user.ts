export interface User {
  id: number;
  username: string;
  email: string;
  createdAt: string; // LocalDateTime → string JSON
  roleName: string;
}