// src/app/models/user.ts :

export interface User {
  id: number;
  username: string;
  email: string;
  createdAt: string; // LocalDateTime → string JSON
  roleId: number;
  roleName: string;
}