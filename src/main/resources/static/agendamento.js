const botao = document.querySelectorAll('.botao');
const modal = document.querySelector('.modal');
const caixa = document.querySelector('.caixa');
const fechar = document.querySelector('.fechar');
const enviar = document.querySelector('.enviar');

botao.forEach(function(botao) {
    botao.addEventListener('click', function() {
        modal.classList.add('aberto');
    });
});

document.querySelector('.fechar').addEventListener('click', function() {
    modal.classList.remove('aberto');
});

modal.addEventListener('click', function(event) {
    if (event.target === modal) {
        modal.classList.remove('aberto');
    }
});

caixa.addEventListener('click', function(event) {
    event.stopPropagation();
});

enviar.addEventListener('click', function(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const data = document.getElementById('data').value;
    const servico = document.getElementById('servico').value;
    const horario = document.getElementById('horario').value;
    const contato = document.getElementById('contato').value;

    if(nome === '' || servico === '' || data === '' || horario === '' || contato === ''){
        alert('Por favor, preencha todos os campos antes de confirmar o agendamento.');
        return;
    }

    // Monta a data no formato que o banco entende
    const dataHorario = data + 'T' + horario + ':00';

    // Salva no banco de dados
    fetch('/agendar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            nomeCliente: nome,
            telefone: contato,
            servico: servico,
            dataHorario: dataHorario
        })
    }).then(() => {
        // Envia pro WhatsApp depois de salvar
        const mensagem =
            ' NOVO AGENDAMENTO - VFBarber' +
            '\n👤Nome: ' + nome +
            '\n✂️Servico: ' + servico +
            '\n📅Data: ' + data +
            '\n⏰Horario: ' + horario +
            '\n📞Contato: ' + contato;

        const telefone = '5511943382780';
        window.location.href = 'https://wa.me/' + telefone + '?text=' + encodeURIComponent(mensagem);
    });
});