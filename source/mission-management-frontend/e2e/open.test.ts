import { test, expect } from "@playwright/test";

// test('go to open and fill form', async ({ page }) => {
//   await page.goto('http://localhost:5173/open');
//   await expect(page.getByRole("textbox", {name: 'Mission Name'})).toBeVisible()
//   await expect(page.getByText("Successfully submitted")).not.toBeVisible()
//   await page.getByRole('textbox', { name: 'Mission Name' }).click();
//   await page.getByRole('textbox', { name: 'Mission Name' }).fill('Test');
//   await page.getByRole('textbox', { name: 'Start Date' }).click();
//   await page.getByText('20', { exact: true }).click();
//   await page.locator('form').click();
//   await page.locator('#party-access').selectOption('Fire Department A');
//   await page.locator('#sub-missions').selectOption('Mission ABC');
//   await page.getByRole('button', { name: 'Create Mission' }).click();
//   await expect(page.getByText("Successfully submitted")).toBeVisible()
// });
