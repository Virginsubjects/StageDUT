global mu gamma beta N nu sigma rho np ns;
np = 5; ns = 2;
mu = repmat([0.000365 0.00137], np, 1);
gamma = repmat([0.25 0.233], np, 1);
beta = repmat([0 0.21; 0 0.42], [1 1 np ]);
sigma = repmat([0.5 0.67], np, 1);
nu = repmat(0.00274, np, ns);
N = repmat([500 5000], np, 1);
I = zeros(np, ns);
p = 1; 
s = 2; 
I(p,s)=10; %initial number of infected birds
S = N-I;
E = zeros(np, ns);
R = zeros(np, ns);
map = 
[0 1 0 1 1; 
 1 0 1 1 1; 
 0 1 0 1 0;
 1 1 1 0 0;
 1 1 0 0 0] ;
rho = cat(3,map*0.03,map*0.1) ;
tMax = 550; %for solving the equations
options=[];
S = reshape(S,[1 ns * np ]); E = reshape(E,[1 ns * np ]);
I = reshape(I,[1 ns * np ]); R = reshape(R,[1 ns * np ]);
[T,Y]=ode45(@rightSideSEIRSInfluenza,[0 tMax],[S E I R],options);
%generate curves for the compartments [...]
function res=rightSideSEIRSInfluenza(t, compartments)
global mu gamma beta N nu sigma rho np ns ;
S = reshape(compartments(1: np*ns),[np ns]);
E = reshape(compartments(np*ns+1: 2*np*ns),[np ns]);
I = reshape(compartments(2*np*ns+1: 3*np*ns),[np ns]);
R = reshape(compartments(3*np*ns+1: end),[np ns]);
lambda = zeros(np, ns);
for p = 1:np
  for s = 1:ns
    lambda(p,s) = sum(beta(s,:,p).*I(p,:)./N(p,:));
  end
end
deltaS = zeros(np,ns); deltaE = zeros(np,ns);
deltaI = zeros(np,ns); deltaR = zeros(np,ns);
for s = 1:ns
  deltaS(:,s) = rho(:,:,s)*S(:,s)-sum(rho(:,:,s))'.* S(:,s);
  deltaE(:,s) = rho(:,:,s)*E(:,s)-sum(rho(:,:,s))'.* E(:,s);
  deltaI(:,s) = rho(:,:,s)*I(:,s)-sum(rho(:,:,s))'.* I(:,s);
  deltaR(:,s) = rho(:,:,s)*R(:,s)-sum(rho(:,:,s))'.* R(:,s);
end
dSdt = mu.*N + nu.*R - lambda.*S - mu.*S + deltaS ;
dEdt = lambda.*S - sigma.*E - mu.*E + deltaE ;
dIdt = sigma.*E - gamma.*I - mu.*I + deltaI ;
dRdt = gamma.*I - mu.*R - nu.*R + deltaR ;
dSdt = reshape(dSdt ,[1 np * ns ]);
dEdt = reshape(dEdt ,[1 np * ns ]);
dIdt = reshape(dIdt ,[1 np * np ]);
dRdt = reshape(dRdt ,[1 np * ns ]);
res = [dSdt dEdt dIdt dRdt]';
