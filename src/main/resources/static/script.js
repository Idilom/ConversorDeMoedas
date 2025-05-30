async function convert() {
  const from = document.getElementById("from").value;
  const to = document.getElementById("to").value;
  const amount = document.getElementById("amount").value;

  if (!amount || amount <= 0) {
    alert("Digite um valor válido.");
    return;
  }

  try {
    const response = await fetch(
      `http://localhost:8080/api/convert?from=${from}&to=${to}&amount=${amount}`
    );
    const data = await response.json();
    document.getElementById(
      "result"
    ).innerText = `${amount} ${from} = ${data.converted} ${to}`;
  } catch (error) {
    document.getElementById("result").innerText =
      "Erro na conversão. Verifique o backend.";
  }
}
